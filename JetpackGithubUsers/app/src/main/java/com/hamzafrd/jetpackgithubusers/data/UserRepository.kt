package com.hamzafrd.jetpackgithubusers.data

import com.hamzafrd.jetpackgithubusers.model.FakeHeroDataSource
import com.hamzafrd.jetpackgithubusers.model.HeroList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class UserRepository {
    private val users = mutableListOf<HeroList>()

    init {
        if (users.isEmpty()) {
            FakeHeroDataSource.heroes.forEach {
                users.add(HeroList(it, 0))
            }
        }
    }

    fun getAllHero(): Flow<List<HeroList>> {
        return flowOf(users)
    }


    fun searchHero(query: String): Flow<List<HeroList>> {
        return flowOf(users.filter {
            it.hero.title.contains(query, ignoreCase = true)
        })
    }

    fun getHeroById(rewardId: Long): HeroList {
        return users.first {
            it.hero.id == rewardId
        }
    }

    fun updateFavoriteHero(rewardId: Long, newCountValue: Int): Flow<Boolean> {
        val index = users.indexOfFirst { it.hero.id == rewardId }
        val result = if (index >= 0) {
            val orderReward = users[index]
            users[index] =
                orderReward.copy(hero = orderReward.hero, isFavorite = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getFavoriteHero(): Flow<List<HeroList>> {
        return getAllHero()
            .map { hero ->
                hero.filter {
                    it.isFavorite != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository =
            instance ?: synchronized(this) {
                UserRepository().apply {
                    instance = this
                }
            }
    }
}