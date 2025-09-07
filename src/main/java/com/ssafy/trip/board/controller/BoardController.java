package com.ssafy.trip.board.controller;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.FileInfo;
import com.ssafy.trip.board.model.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/board")
@Api(tags = {"게시판 컨트롤러 API V1"})
@Slf4j
public class BoardController {
    @Value("${file.path.upload-default}")
    private String uploadPath;

    @Value("${file.path.upload-images}")
    private String uploadImagePath;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        super();
        this.boardService = boardService;
    }

    @ApiOperation(value = "글 등록", notes = "글 정보를 받아 등록")
    @PostMapping("/write" )
    public ResponseEntity<?> write(Board board, @RequestParam(value = "upfile", required = false) MultipartFile file) {
        log.debug("write boardDto : {}", board);
        log.debug("upload : {}", file);
//		log 관련 설정.
        log.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
        try {
            if (file != null && !file.isEmpty()) {
                log.debug("MultipartFile.isEmpty : {}", file.isEmpty());
    //			String realPath = servletContext.getRealPath(UPLOAD_PATH);
    //			String realPath = servletContext.getRealPath("/resources/img");
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveFolder = uploadPath + File.separator + today;
                log.debug("저장 폴더 : {}", saveFolder);
                File folder = new File(saveFolder);
                if (!folder.exists())
                    folder.mkdirs();
                FileInfo fileInfo = new FileInfo();

                String originalFileName = file.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString()
                            + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    fileInfo.setSaveFolder(today);
                    fileInfo.setOriginalFile(originalFileName);
                    fileInfo.setSaveFile(saveFileName);
                    log.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", file.getOriginalFilename(), saveFileName);
                    file.transferTo(new File(folder, saveFileName));
                }
                board.setFileInfo(fileInfo);
            }

            boardService.writeArticle(board);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }


    @ApiOperation(value = "글 목록", notes = "조회 조건에 따라 글 목록 반환")
    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam Map<String, String> map) {
        log.debug("list parameter pgno : {}", map.get("pgno"));
        try {
            List<Board> boards = boardService.listArticle(map);
            return ResponseEntity.status(HttpStatus.OK).body(boards);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
        
    }


    @ApiOperation(value = "글 읽기", notes = "특정 글 내용 확인")
    @GetMapping("/{articleNo}")
    public ResponseEntity<?> view(@PathVariable int articleNo) {
        log.debug("view articleNo : {}", articleNo);
        try{
            Board board = boardService.getArticle(articleNo);
            boardService.updateHit(articleNo);
            return ResponseEntity.status(HttpStatus.OK).body(board);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }


    @ApiOperation(value = "글 수정", notes = "특정 글 내용 수정")
    @PutMapping("/modify")
    public ResponseEntity<?> modify(Board board, @RequestParam(value = "upfile", required = false) MultipartFile file) {
        log.debug("modify boardDto : {}", board);
        try {
            if (file != null && !file.isEmpty()) {
                log.debug("MultipartFile.isEmpty : {}", file.isEmpty());
                //			String realPath = servletContext.getRealPath(UPLOAD_PATH);
                //			String realPath = servletContext.getRealPath("/resources/img");
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveFolder = uploadPath + File.separator + today;
                log.debug("저장 폴더 : {}", saveFolder);
                File folder = new File(saveFolder);
                if (!folder.exists())
                    folder.mkdirs();
                FileInfo fileInfo = new FileInfo();

                String originalFileName = file.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString()
                            + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    fileInfo.setSaveFolder(today);
                    fileInfo.setOriginalFile(originalFileName);
                    fileInfo.setSaveFile(saveFileName);
                    log.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", file.getOriginalFilename(), saveFileName);
                    file.transferTo(new File(folder, saveFileName));
                }
                board.setFileInfo(fileInfo);
            }

            boardService.modifyArticle(board, uploadPath);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }


    @ApiOperation(value = "글 삭제", notes = "선택된 글 삭제")
    @DeleteMapping("/{articleNo}")
    public ResponseEntity<?> delete(@PathVariable int articleNo) {
        log.debug("delete articleNo : {}", articleNo);
//		boardService.deleteArticle(articleNo, servletContext.getRealPath(UPLOAD_PATH));
        try {
            boardService.deleteArticle(articleNo, uploadPath);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    @CrossOrigin
    @GetMapping("/display")
    public ResponseEntity<?> display(@RequestParam Map<String, String> map) {
        log.debug("display debug : {}", map);
        String savePath = uploadPath + map.get("today")+ "/" + map.get("savefile");
        Path path = Paths.get(savePath);
        try {
            Resource resource = new InputStreamResource(Files.newInputStream(path));
            if(!resource.exists())
                return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
            HttpHeaders header = new HttpHeaders();
            Path filePath = null;

            filePath = Paths.get(savePath);
            header.add("Content-type", Files.probeContentType(filePath));

            return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        } catch (IOException e) {
            exceptionHandling(e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<String>("errors : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
