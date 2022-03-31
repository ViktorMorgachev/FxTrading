package com.learning.lessons.data.api.lesson.mock

import com.learning.lessons.data.api.lesson.ApiLesson

class MockData {
    companion object{
        // описание внутри урока/курса ниже идет
        // заголовок урока выше
        val mockApiLesson = ApiLesson(
            categories = listOf("Figures","Candles"),
            description = "Base Description",
            difficulty = 1,
            dislikes = 100,
            likes = 130,
            duration = "30:02",
            id = 1,
            active = true,
            language = "RU",
            promo_image_url = "gs://fx-trading-lessons.appspot.com/images/lessons_promo_images/promo_lesson.png",
            region = "RU",
            sort_order = 1,
            speaker_name = "Ali Aby Nerden",
            tags = listOf("Figures", "Candles", "Easy"),
            text_version_link = "",
            title = "It's first testing video",
            video_url = "https://www.youtube.com/watch?v=dpy0_b9oxpM&list=RDMM&start_radio=1&rv=Py5dTPmlNhI")

        val mockApiLessons = listOf<ApiLesson>(mockApiLesson,
            mockApiLesson.copy(categories = listOf("Currencies", "ForexBasics"),
                duration = "16:20",
                title = "It's second testing video",
                likes = 160),
            mockApiLesson.copy(categories = listOf("Figures","Indicators"),
                duration = "12:40",
                title = "It's third testing video",
                likes = 10),
            mockApiLesson.copy(categories = listOf("Candles","ForexBasics"),
                duration = "22:01",
                title = "It's four testing video",
                likes = 54),
            mockApiLesson.copy(categories = listOf("Figures","Indicators","Currencies"),
                duration = "54:30",
                title = "It's five testing video",
                likes = 12))
    }
}