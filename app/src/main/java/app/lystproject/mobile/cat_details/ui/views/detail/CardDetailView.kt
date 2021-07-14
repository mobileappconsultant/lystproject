package app.lystproject.mobile.cat_details.ui.views.detail

import app.lystproject.mobile.databinding.LayoutCatDetailBinding
import app.lystproject.presentation_android.UIComponent

class CardDetailView(
    private val view: LayoutCatDetailBinding
): UIComponent<CatDetailsViewState>() {

    override fun render(state: CatDetailsViewState) {
        view.txtDescription.text = state.description
        view.ratingBar.rating  = state.energyLevel ?: 0f
        view.txtCatTitle.text = state.name
        view.txtSubTitle.text = state.country
        view.txtAbout.text = state.wikipedia_url
    }
}