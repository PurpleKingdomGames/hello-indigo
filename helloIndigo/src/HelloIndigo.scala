import indigo._
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object HelloIndigo extends IndigoSandbox[Unit, Unit] {

  val config: indigo.GameConfig =
    GameConfig.default

  val animations: Set[indigo.Animation] =
    Set()

  val assets: Set[indigo.AssetType] =
    Set()

  val fonts: Set[indigo.FontInfo] =
    Set()

  def setup(
      assetCollection: indigo.AssetCollection,
      dice: indigo.Dice
  ): indigo.Startup[indigo.StartupErrors, Unit] =
    Startup.Success(())

  def initialModel(startupData: Unit): Unit =
    ()

  def updateModel(
      context: indigo.FrameContext,
      model: Unit
  ): indigo.GlobalEvent => indigo.Outcome[Unit] =
    _ => Outcome(())

  def present(
      context: indigo.FrameContext,
      model: Unit
  ): indigo.SceneUpdateFragment =
    SceneUpdateFragment.empty

}
