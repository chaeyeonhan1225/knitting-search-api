package com.lemonearthchoco.knittingsearchapi.controller

import com.lemonearthchoco.knittingsearchapi.service.RavelryService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val ravelryService: RavelryService
) {
    @GetMapping("/callback")
    fun callback(@RequestParam(required = false) code: String?, @RequestParam(required = false) state: String?, request: HttpServletRequest): String {
        code?.let { ravelryService.getToken(code) }
        return "Login Success"
    }
}