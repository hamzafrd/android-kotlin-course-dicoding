package com.hamzafrd.springboot.service

import com.hamzafrd.springboot.model.CreateProductRequest
import com.hamzafrd.springboot.model.ListProductRequest
import com.hamzafrd.springboot.model.ProductResponse
import com.hamzafrd.springboot.model.UpdateProductRequest

interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id:String):ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest):ProductResponse

    fun delete(id:String)

    fun listProduct(listProductRequest: ListProductRequest): List<ProductResponse>
}