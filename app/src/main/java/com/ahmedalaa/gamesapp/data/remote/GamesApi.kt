/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ahmedalaa.gamesapp.data.remote

import com.ahmedalaa.gamesapp.data.remote.response.GamePosterReponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface GamesApi {

  @GET("api/games?dates=2019-01-01,2019-12-31&ordering=-added")
  suspend fun fetchGamesList(): ApiResponse<GamePosterReponse>
}
