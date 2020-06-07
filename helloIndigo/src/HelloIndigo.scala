import indigo._
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object HelloIndigo extends IndigoSandbox[Unit, Unit] {

  val config: indigo.GameConfig =
    GameConfig.default.withMagnification(3)

  val animations: Set[indigo.Animation] =
    Set()

  val assetName = AssetName("dots")

  val assets: Set[indigo.AssetType] = Set(
    AssetType.Image(AssetName("dots"), AssetPath("assets/dots.png"))
  )

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
    SceneUpdateFragment(
      Graphic(Rectangle(0, 0, 32, 32), 1, Material.Textured(assetName))
    )

}
