package com.javi.domain.use_case

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class BaseUseCase(val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default)