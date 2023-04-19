package jaeboard.board.controller;

import jaeboard.board.entity.item.Book;
import jaeboard.board.entity.item.Item;
import jaeboard.board.service.ItemService;
import jaeboard.board.web.BookForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String itemCreateForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "/items/createItemForm";
    }

    @PostMapping("/items/new")
    public String itemCreate(@Valid BookForm form) {
        Book book = new Book();

        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setIsbn(form.getIsbn());
        book.setAuthor(form.getAuthor());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String itemList(Model model){
        List<Item> items = itemService.itemList();
        model.addAttribute("items",items);
        return "/items/itemList";
    }

    @GetMapping("/items/{id}/edit")
    public String updateItemForm(@PathVariable Long id, Model model) {
        Book item = (Book) itemService.findItem(id);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setIsbn(item.getIsbn());
        form.setAuthor(item.getAuthor());
        form.setStockQuantity(item.getStockQuantity());

        model.addAttribute("form", form);
        return "/items/updateItemForm";
    }

/*    @PostMapping("/items/{id}/edit")
    public String updateItem(@ModelAttribute BookForm form){    // Entity를 이용한 변경 방법
        Book book = new Book();

        book.setName(form.getName());
        book.setId(form.getId());
        book.setPrice(form.getPrice());
        book.setIsbn(form.getIsbn());
        book.setAuthor(form.getAuthor());
        book.setStockQuantity(form.getStockQuantity());

        itemService.saveItem(book);
        return "redirect:/";
    }*/

    @PostMapping("/items/{id}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form, @PathVariable Long id){ // 변경 감지 사용법
        itemService.updateItem(id, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/";
    }
}
