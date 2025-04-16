package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정하기(reqDTO, id, sessionUser.getId());

        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.업데이트글보기(id, sessionUser.getId());
        request.setAttribute("model", board);
        return "board/update-form";
    }

    @GetMapping("/v2/board/{id}")
    public @ResponseBody BoardResponse.DetailDTO v2Detail(@PathVariable("id") Integer id) {

        Integer sessionUserId = 1;
        BoardResponse.DetailDTO detailDTO = boardService.글상세보기(id, sessionUserId);

        return detailDTO;
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = (sessionUser == null ? null : sessionUser.getId());
        BoardResponse.DetailDTO detailDTO = boardService.글상세보기(id, sessionUserId);
        request.setAttribute("model", detailDTO);
        return "board/detail";
    }

    @GetMapping("/")
    public String list(HttpServletRequest request, @RequestParam(required = false, value = "page", defaultValue = "0") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            request.setAttribute("model", boardService.글목록보기(null, page));
        } else {
            request.setAttribute("model", boardService.글목록보기(sessionUser.getId(), page));
        }

        return "board/list";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(saveDTO, sessionUser);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }
}
