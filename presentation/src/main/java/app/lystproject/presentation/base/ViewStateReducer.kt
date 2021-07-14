package app.lystproject.presentation.base

public interface ViewStateReducer<S : ScreenState, R : ViewResult> {
    public fun reduce(oldState: S, result: R): S
}
