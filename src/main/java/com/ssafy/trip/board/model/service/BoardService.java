package com.ssafy.trip.board.model.service;

import com.ssafy.trip.board.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {

    void writeArticle(Board board) throws Exception;
    List<Board> listArticle(Map<String, String> map) throws Exception;
    Board getArticle(int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;

    void modifyArticle(Board board, String path) throws Exception;
    void deleteArticle(int articleNo, String path) throws Exception;


}
