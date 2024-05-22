package com.dicoding.courseschedule.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.courseschedule.data.DataCourseName.COL_DAY
import com.dicoding.courseschedule.data.DataCourseName.COL_ID
import com.dicoding.courseschedule.data.DataCourseName.TABLE_NAME

//TODO 2 : Define data access object (DAO)
@Dao
interface CourseDao {

    @RawQuery(observedEntities = [Course::class])
    fun getNearestSchedule(query: SupportSQLiteQuery): LiveData<Course?>

    @RawQuery(observedEntities = [Course::class])
    fun getAll(query: SupportSQLiteQuery): DataSource.Factory<Int, Course>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COL_ID = :id")
    fun getCourse(id: Int): LiveData<Course>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COL_DAY = :day")
    fun getTodaySchedule(day: Int): List<Course>

    @Insert
    fun insert(course: Course)

    @Delete
    fun delete(course: Course)
}