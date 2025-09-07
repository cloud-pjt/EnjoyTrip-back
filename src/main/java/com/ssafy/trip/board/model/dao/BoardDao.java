package com.ssafy.trip.board.model.dao;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    void writeArticle(Board board) throws SQLException;

    void registerFile(Board board) throws Exception;

    List<Board> listArticle(Map<String, Object> param) throws SQLException;

    int getTotalArticleCount(Map<String, Object> param) throws SQLException;

    Board getArticle(int articleNo) throws SQLException;

    void updateHit(int articleNo) throws SQLException;

    void modifyArticle(Board board) throws SQLException;

    void deleteFile(int articleNo) throws Exception;
    void updateFile(FileInfo fileInfo) throws SQLException;

    void deleteArticle(int articleNo) throws SQLException;

    FileInfo fileInfo(int articleNo) throws Exception;


}
