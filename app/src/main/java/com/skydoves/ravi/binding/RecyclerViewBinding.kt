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

package com.skydoves.ravi.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.ravi.extension.bindResource
import com.skydoves.ravi.extension.visible
import com.skydoves.ravi.models.Resource
import com.skydoves.ravi.models.Review
import com.skydoves.ravi.models.Status
import com.skydoves.ravi.models.Video
import com.skydoves.ravi.models.entity.Movie
import com.skydoves.ravi.models.entity.Person
import com.skydoves.ravi.models.entity.Tv
import com.skydoves.ravi.view.adapter.MovieListAdapter
import com.skydoves.ravi.view.adapter.PeopleAdapter
import com.skydoves.ravi.view.adapter.ReviewListAdapter
import com.skydoves.ravi.view.adapter.TvListAdapter
import com.skydoves.ravi.view.adapter.VideoListAdapter
import com.skydoves.ravi.view.ui.main.MainActivityViewModel
import com.skydoves.whatif.whatIfNotNullOrEmpty

@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(view: RecyclerView, adapter: BaseAdapter) {
  view.adapter = adapter
}

@BindingAdapter("adapterMovieList")
fun bindAdapterMovieList(view: RecyclerView, resource: Resource<List<Movie>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? MovieListAdapter
    adapter?.addMovieList(it)
  }
}

@BindingAdapter("moviePagination")
fun bindMoviePagination(view: RecyclerView, viewModel: MainActivityViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.getTvListValues()?.status == Status.LOADING },
    loadMore = { viewModel.postMoviePage(it) },
    onLast = { false }
  ).run {
    currentPage = 1
  }
}

@BindingAdapter("adapterPersonList")
fun bindAdapterPersonList(view: RecyclerView, resource: Resource<List<Person>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? PeopleAdapter
    adapter?.addPeople(it)
  }
}

@BindingAdapter("personPagination")
fun bindPersonPagination(view: RecyclerView, viewModel: MainActivityViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.getPeopleValues()?.status == Status.LOADING },
    loadMore = { viewModel.postPeoplePage(it) },
    onLast = { false }
  ).run {
    currentPage = 1
  }
}

@BindingAdapter("adapterTvList")
fun bindAdapterTvList(view: RecyclerView, resource: Resource<List<Tv>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? TvListAdapter
    adapter?.addTvList(it)
  }
}

@BindingAdapter("tvPagination")
fun bindTvPagination(view: RecyclerView, viewModel: MainActivityViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.getTvListValues()?.status == Status.LOADING },
    loadMore = { viewModel.postTvPage(it) },
    onLast = { false }
  ).run {
    currentPage = 1
  }
}

@BindingAdapter("adapterVideoList")
fun bindAdapterVideoList(view: RecyclerView, resource: Resource<List<Video>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? VideoListAdapter
    adapter?.addVideoList(it)
    it.data.whatIfNotNullOrEmpty {
      view.visible()
    }
  }
}

@BindingAdapter("adapterReviewList")
fun bindAdapterReviewList(view: RecyclerView, resource: Resource<List<Review>>?) {
  view.bindResource(resource) {
    val adapter = view.adapter as? ReviewListAdapter
    adapter?.addReviewList(it)
    it.data.whatIfNotNullOrEmpty {
      view.visible()
      view.setHasFixedSize(true)
    }
  }
}
