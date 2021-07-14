package app.lystproject.mobile.cat_details.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import app.lystproject.mobile.R
import app.lystproject.mobile.cat_details.presentation.CatDetailViewStateMachine
import app.lystproject.mobile.cat_details.presentation.DetailComponentManager
import app.lystproject.mobile.cat_details.presentation.viewstate.CatDetailViewState
import app.lystproject.mobile.cat_details.ui.views.detail.CardDetailView
import app.lystproject.mobile.cat_details.ui.views.error.DetailErrorView
import app.lystproject.mobile.cat_details.ui.views.poster.PosterView
import app.lystproject.mobile.cat_search.model.CatDetailModel
import app.lystproject.mobile.core.extension.NavigateBack
import app.lystproject.mobile.databinding.FragmentCatDetailsBinding
import app.lystproject.presentation.base.ViewIntent
import app.lystproject.presentation_android.AssistedCreator
import app.lystproject.presentation_android.assistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class CatDetailComponentManager @AssistedInject constructor(
    stateMachine: CatDetailViewStateMachine.Factory,
    @Assisted catDetailModel: CatDetailModel
) : DetailComponentManager(stateMachine.create(catDetailModel)) {

    @AssistedFactory
    interface Creator : AssistedCreator<CatDetailModel, CatDetailComponentManager>
}

data class LoadCatDetailIntent(val cat: CatDetailModel) : ViewIntent


@AndroidEntryPoint
class CatDetailsFragment : Fragment(R.layout.fragment_cat_details) {

    @Inject
    lateinit var goBack: NavigateBack

    @Inject
    lateinit var creator: CatDetailComponentManager.Creator


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: CatDetailsFragmentArgs by navArgs()

        val componentManager: CatDetailComponentManager by viewModels {
            assistedFactory(creator, args.cat)
        }

        val binding = FragmentCatDetailsBinding.bind(view)

        componentManager.run {
            subscribe(
                component = DetailErrorView(
                    view = binding.detailErrorState,
                    cat = args.cat
                ),
                stateTransform = CatDetailViewState::errorViewState
            )
            subscribe(
                component = PosterView(
                    view = binding.layoutPosterItem,
                    navigateUp = goBack
                ),
                stateTransform = CatDetailViewState::posterViewState
            )

            subscribe(
                component = CardDetailView(
                    view = binding.layoutCatDetails
                ),
                stateTransform = CatDetailViewState::catDetailViewState
            )

            disposeAll(viewLifecycleOwner)
        }
    }
}