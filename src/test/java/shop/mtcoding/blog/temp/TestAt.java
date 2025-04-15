package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;

import java.util.List;

public class TestAt {
    BoardRepository boardRepository;


    @Test
    public void findById_test() {
        int id = 1;

        Board board = boardRepository.findById(id);

        System.out.println("id: " + board.getId());
        System.out.println("title: " + board.getTitle());
        System.out.println("content: " + board.getContent());
        System.out.println("createAt: " + board.getCreatedAt());
    }

    @Test
    public void findAll_test() {

        List<Board> boardList = boardRepository.findAll();

        for (Board board : boardList) {
            System.out.println("id: " + board.getId());
            System.out.println("title: " + board.getTitle());
            System.out.println("content: " + board.getContent());
            System.out.println("createAt: " + board.getCreatedAt());
        }
    }

    @Test
    public void insert_test() {
        String title = "제목1";
        String content = "내용1";

        boardRepository.insert(title, content);
        List<Board> boardList = boardRepository.findAll();
        System.out.println("id: " + boardList.get(0).getId());
        System.out.println("title: " + boardList.get(0).getTitle());
        System.out.println("content: " + boardList.get(0).getContent());
        System.out.println("createAt: " + boardList.get(0).getCreatedAt());
        System.out.println("length: " + boardList.size());
    }

    @Test
    public void deleteById_test() {

        int idNo1 = 1;
        int idNo2 = 2;

        boardRepository.deleteById(idNo1);

        Board board1 = boardRepository.findById(idNo1);
        Board board2 = boardRepository.findById(idNo2);
        List<Board> boardList = boardRepository.findAll();
        System.out.println(board1);
        System.out.println(board2);
        System.out.println("length: " + boardList.size());
    }

    @Test
    public void update_test() {

        int id = 1;
        String title = "제목1";
        String content = "내용1";

        boardRepository.update(id, title, content);

        Board board = boardRepository.findById(id);

        System.out.println("id: " + board.getId());
        System.out.println("title: " + board.getTitle());
        System.out.println("content: " + board.getContent());
        System.out.println("createAt: " + board.getCreatedAt());
    }


}
