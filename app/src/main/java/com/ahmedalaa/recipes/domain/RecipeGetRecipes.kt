package com.ahmedalaa.recipes.domain

import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.repository.IRecipesRepository
import com.ahmedalaa.recipes.utils.ApiResponse
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetRecipes(
    private val executionContext: CoroutineContext,
    private val postExecutionContext: CoroutineContext,
    @Inject val repository: IRecipesRepository
) : BaseUseCase<List<Recipe>, Nothing>(executionContext, postExecutionContext) {
    override suspend fun run(channel: SendChannel<List<Recipe>>, params: Nothing?) {
        repository.getRecipes().collect {
            channel.send((it))
        }
    }

}