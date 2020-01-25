package hello.world

import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html.{button, div, h1, onClick}

@react class ButtonComponent extends Component {
  type Props = Unit // no props
  case class State(buttonPresses: Int)

  def initialState = State(0)

  def render = {
    div(
      h1(s"Clicked ${state.buttonPresses} times!"),
      button(onClick := (_ => {
        setState(State(state.buttonPresses + 1))
      }))(
        "Click Me!"
      )
    )
  }
}