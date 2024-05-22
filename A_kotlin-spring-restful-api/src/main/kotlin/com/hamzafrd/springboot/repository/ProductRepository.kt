package com.hamzafrd.springboot.repository

import com.hamzafrd.springboot.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product,String>