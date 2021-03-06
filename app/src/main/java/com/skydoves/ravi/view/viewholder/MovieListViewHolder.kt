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

package com.skydoves.ravi.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.ravi.api.Api
import com.skydoves.ravi.models.entity.Movie
import com.skydoves.ravi.view.ui.details.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.item_poster.view.item_poster_palette
import kotlinx.android.synthetic.main.item_poster.view.item_poster_post
import kotlinx.android.synthetic.main.item_poster.view.item_poster_title

class MovieListViewHolder constructor(
  val view: View
) : BaseViewHolder(view) {

  private lateinit var movie: Movie

  @Throws(Exception::class)
  override fun bindData(data: Any) {
    if (data is Movie) {
      movie = data
      drawItem()
    }
  }

  private fun drawItem() {
    itemView.run {
      item_poster_title.text = movie.title
      movie.poster_path?.let {
        Glide.with(context)
          .load(Api.getPosterPath(it))
          .listener(GlidePalette.with(Api.getPosterPath(it))
            .use(BitmapPalette.Profile.VIBRANT)
            .intoBackground(item_poster_palette)
            .crossfade(true))
          .into(item_poster_post)
      }
    }
  }

  override fun onClick(v: View?) = MovieDetailActivity.startActivityModel(context(), movie)

  override fun onLongClick(v: View?) = false
}
