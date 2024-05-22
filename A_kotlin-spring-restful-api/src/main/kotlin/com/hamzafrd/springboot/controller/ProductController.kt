package com.hamzafrd.springboot.controller

import com.hamzafrd.springboot.model.*
import com.hamzafrd.springboot.service.ProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
        value = ["api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @GetMapping(
        value = ["api/products/{idProducts}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProducts") id: String): WebResponse<ProductResponse> {
        val productResponse = productService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @PutMapping(
        value = ["api/products/{idProducts}"],
        consumes = ["application/json"],
        produces = ["application/json"],
    )
    fun updateProduct(
        @PathVariable("idProducts") id: String,
        @RequestBody body: UpdateProductRequest
    ): WebResponse<ProductResponse> {
        val productResponse = productService.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @DeleteMapping(
        value = ["api/products/{idProducts}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProducts") id: String): WebResponse<String> {
        productService.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Product with id $id has been deleted"
        )
    }

    @GetMapping(
        value = ["api/products"],
        produces = ["application/json"]
    )
    fun listProducts(
        @RequestParam(value = "size", defaultValue = "10")
        size: Int,
        @RequestParam(value = "page", defaultValue = "0")
        page: Int
    ): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(size = size, page = page)
        val response = productService.listProduct(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}