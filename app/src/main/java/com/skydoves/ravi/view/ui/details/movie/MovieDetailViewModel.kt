/*
 * The MIT License (MIT)
 *
 * Designed and developed by 2018 skydoves (Jaewoong Eum)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.skydoves.ravi.view.ui.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.skydoves.ravi.models.Keyword
import com.skydoves.ravi.models.Resource
import com.skydoves.ravi.models.Review
import com.skydoves.ravi.models.Video
import com.skydoves.ravi.repository.MovieRepository
import com.skydoves.ravi.utils.AbsentLiveData
import javax.inject.Inject
import timber.log.Timber

class MovieDetailViewModel @Inject constructor(
  private val repository: MovieRepository
) : ViewModel() {

  private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()
  val keywordListLiveData: LiveData<Resource<List<Keyword>>>
  val videoListLiveData: LiveData<Resource<List<Video>>>
  val reviewListLiveData: LiveData<Resource<List<Review>>>

  init {
    Timber.d("Injection MovieDetailViewModel")

    this.keywordListLiveData = movieIdLiveData.switchMap {
      movieIdLiveData.value?.let {
        repository.loadKeywordList(it)
      } ?: AbsentLiveData.create()
    }

    this.videoListLiveData = movieIdLiveData.switchMap {
      movieIdLiveData.value?.let {
        repository.loadVideoList(it)
      } ?: AbsentLiveData.create()
    }

    this.reviewListLiveData = movieIdLiveData.switchMap {
      movieIdLiveData.value?.let {
        repository.loadReviewsList(it)
      } ?: AbsentLiveData.create()
    }
  }

  fun postMovieId(id: Int) = movieIdLiveData.postValue(id)
}