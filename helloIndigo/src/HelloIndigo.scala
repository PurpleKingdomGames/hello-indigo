import indigo._
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object HelloIndigo extends IndigoSandbox[Unit, Model] {

  val magnification = 3

  val config: GameConfig =
    GameConfig.default.withMagnification(magnification)

  val animations: Set[Animation] =
    Set()

  val assetName = AssetName("dots")

  val assets: Set[AssetType] =
    Set(
      AssetType.Image(AssetName("dots"), AssetPath("assets/dots.png"))
    )

  val fonts: Set[FontInfo] =
    Set()

  def setup(
      assetCollection: AssetCollection,
      dice: Dice
  ): Startup[Unit] =
    Startup.Success(())

  def initialModel(startupData: Unit): Model =
    Model.initial(
      config.viewport.giveDimensions(magnification).center
    )

  def updateModel(
      context: FrameContext[Unit],
      model: Model
  ): GlobalEvent => Outcome[Model] = {
    case MouseEvent.Click(x, y) =>
      val adjustedPosition = Point(x, y) - model.center

      Outcome(
        model.addDot(
          Dot(
            Point.distanceBetween(model.center, Point(x, y)).toInt,
            Radians(
              Math.atan2(
                adjustedPosition.x.toDouble,
                adjustedPosition.y.toDouble
              )
            )
          )
        )
      )

    case FrameTick =>
      Outcome(model.update(context.delta))

    case _ =>
      Outcome(model)
  }

  def present(
      context: FrameContext[Unit],
      model: Model
  ): SceneUpdateFragment =
    SceneUpdateFragment(
      Graphic(Rectangle(0, 0, 32, 32), 1, Material.Textured(assetName))
    ).addGameLayerNodes(
      drawDots(model.center, model.dots)
    )

  def drawDots(
      center: Point,
      dots: List[Dot]
  ): List[Graphic] =
    dots.map { dot =>
      val position = Point(
        (Math.sin(dot.angle.value) * dot.orbitDistance + center.x).toInt,
        (Math.cos(dot.angle.value) * dot.orbitDistance + center.y).toInt
      )

      Graphic(Rectangle(0, 0, 32, 32), 1, Material.Textured(assetName))
        .withCrop(Rectangle(16, 16, 16, 16))
        .withRef(8, 8)
        .moveTo(position)
    }

}

case class Model(center: Point, dots: List[Dot]) {
  def addDot(dot: Dot): Model =
    this.copy(dots = dot :: dots)

  def update(timeDelta: Seconds): Model =
    this.copy(dots = dots.map(_.update(timeDelta)))
}
object Model {
  def initial(center: Point): Model = Model(center, Nil)
}
case class Dot(orbitDistance: Int, angle: Radians) {
  def update(timeDelta: Seconds): Dot =
    this.copy(angle = angle + Radians.fromSeconds(timeDelta))
}
