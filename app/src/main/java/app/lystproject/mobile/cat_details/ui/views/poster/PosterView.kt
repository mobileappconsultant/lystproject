package app.lystproject.mobile.cat_details.ui.views.poster

import android.graphics.drawable.Drawable
import androidx.core.view.isVisible
import app.lystproject.mobile.databinding.LayoutSliderItemBinding
import app.lystproject.presentation_android.UIComponent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class PosterView(
    private val view: LayoutSliderItemBinding,
    navigateUp: () -> Unit,
) : UIComponent<PosterViewState>() {

    init {
        view.imgBack.setOnClickListener { navigateUp() }
    }

    override fun render(state: PosterViewState) {
        view.posterLoadingView.isVisible = true
        view.run {
            Glide.with(view.root)
                .load(state.imageUrl)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.posterLoadingView.isVisible = false
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.posterLoadingView.isVisible = false
                        return false
                    }
                })
                .into(view.pagerCatImages)
        }
    }
}
