package app.lystproject.presentation.base

public interface ViewIntent
internal object NoOpIntent : ViewIntent

public typealias DispatchIntent = (ViewIntent) -> Unit
