package com.hamzafrd.springboot.service.impl

import com.hamzafrd.springboot.entity.Product
import com.hamzafrd.springboot.error.NotFoundException
import com.hamzafrd.springboot.model.CreateProductRequest
import com.hamzafrd.springboot.model.ListProductRequest
import com.hamzafrd.springboot.model.ProductResponse
import com.hamzafrd.springboot.model.UpdateProductRequest
import com.hamzafrd.springboot.repository.ProductRepository
import com.hamzafrd.springboot.service.ProductService
import com.hamzafrd.springboot.validation.ValidationUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtils: ValidationUtils
) : ProductService {
    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtils.validate(createProductRequest)

        val product = Product(
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )
        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThrowException(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        validationUtils.validate(updateProductRequest)

        val product = findProductByIdOrThrowException(id)

        product.apply {
            name = updateProductRequest.name
            price = updateProductRequest.price
            quantity = updateProductRequest.quantity
            updatedAt = Date()
        }

        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrowException(id)
        productRepository.delete(product)
    }

    override fun listProduct(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val product: List<Product> = page.get().collect(Collectors.toList())

        return product.map { convertProductToProductResponse(it) }
    }

    private fun findProductByIdOrThrowException(id: String): Product {
        return productRepository.findByIdOrNull(id) ?: throw NotFoundException()
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}