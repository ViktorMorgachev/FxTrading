package com.learning.lessons.data.store

import com.learning.lessons.data.api.user_info.ApiUserInfo
import kotlinx.coroutines.flow.MutableStateFlow

interface RepositoryCacheable<T> {
  val cache : MutableStateFlow<T?>
      get() = MutableStateFlow(null)
}