package com.zz.springbootweb.controller;

import com.zz.springbootweb.entity.User;
import com.zz.springbootweb.service.UserService;
import com.zz.springbootweb.utils.Constants;
import com.zz.springbootweb.utils.SearchFilter;
import com.zz.springbootweb.utils.Servlets;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresPermissions("user:list")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Pageable pageable, HttpServletRequest request, Model model) {
        Map<String, SearchFilter> filterMap = SearchFilter.parse(Servlets.getParametersStartingWith(request, Constants.SEARCH_PREFIX));
        Page<User> users = userService.findPage(filterMap, pageable);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequiresPermissions("user:show")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "user/show";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String _new() {
        return "user/new";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user/new";
        }
        userService.createUser(user);

        redirectAttributes.addFlashAttribute("msg", "新增用户成功");
        return "redirect:/user/";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user/edit";
        }

        userService.update(id, user);

        redirectAttributes.addFlashAttribute("msg", "更新用户成功");
        return "redirect:/user/";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes, HttpServletRequest request) {

        userService.delete(id);

        redirectAttributes.addFlashAttribute("msg", "删除用户成功");
        return "redirect:/user/";
    }

}
