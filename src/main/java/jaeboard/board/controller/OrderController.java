package jaeboard.board.controller;

import jaeboard.board.dto.OrderSearchDto;
import jaeboard.board.entity.Member;
import jaeboard.board.entity.Order;
import jaeboard.board.entity.item.Item;
import jaeboard.board.service.ItemService;
import jaeboard.board.service.MemberService;
import jaeboard.board.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String orderForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.itemList();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam Long itemId, @RequestParam int count) {

        orderService.order(memberId, itemId, count);

        return "redirect:/";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearchDto") OrderSearchDto orderSearchDto, Model model) {

        List<Order> orders = orderService.findOrders(orderSearchDto);

        model.addAttribute("orders", orders);

        return "/order/orderList";
    }
}
