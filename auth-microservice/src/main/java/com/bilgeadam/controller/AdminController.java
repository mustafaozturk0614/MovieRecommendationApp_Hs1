package com.bilgeadam.controller;
import static  com.bilgeadam.constant.EndPoints.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AUTH+"/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {




}
