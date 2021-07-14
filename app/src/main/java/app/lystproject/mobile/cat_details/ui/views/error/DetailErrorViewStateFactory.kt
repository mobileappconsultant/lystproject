package app.lystproject.mobile.cat_details.ui.views.error

inline fun DetailErrorViewState.state(
    transform: DetailErrorViewStateFactory.() -> DetailErrorViewState
): DetailErrorViewState = transform(
    DetailErrorViewStateFactory(this)
)

object DetailErrorViewStateFactory {

    private lateinit var state: DetailErrorViewState

    val initialState: DetailErrorViewState
        get() = DetailErrorViewState()

    val Hide: DetailErrorViewState
        get() = DetailErrorViewState()

    operator fun invoke(viewState: DetailErrorViewState): DetailErrorViewStateFactory {
        state = viewState
        return this
    }

    fun DisplayError(catName: String, errorMessage: String): DetailErrorViewState =
        state.copy(
            catName = catName,
            errorMessage = errorMessage,
            showError = true
        )
}
