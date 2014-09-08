package de.fhg.iais.roberta.ast.syntax.action;

import de.fhg.iais.roberta.ast.syntax.Phrase;
import de.fhg.iais.roberta.ast.visitor.AstVisitor;
import de.fhg.iais.roberta.dbc.Assert;

/**
 * This class represents the <b>robActions_motorDiff_turn</b> and <b>robActions_motorDiff_turn_for</b> blocks from Blockly into the AST (abstract syntax tree).
 * Object from this class will generate code for setting the motors in pilot mode and turn on given direction.<br/>
 * <br/>
 * The client must provide the {@link TurnDirection} and {@link MotionParam} (distance the robot should cover and speed).
 */
public class TurnAction<V> extends Action<V> {
    private final TurnDirection direction;
    private final MotionParam<V> param;

    private TurnAction(TurnDirection direction, MotionParam<V> param, boolean disabled, String comment) {
        super(Phrase.Kind.TURN_ACTION, disabled, comment);
        Assert.isTrue(direction != null && param != null);
        this.direction = direction;
        this.param = param;
        setReadOnly();
    }

    /**
     * Creates instance of {@link TurnAction}. This instance is read only and can not be modified.
     * 
     * @param direction {@link TurnDirection} in which the robot will drive,
     * @param param {@link MotionParam} that set up the parameters for the movement of the robot (distance the robot should cover and speed),
     * @param disabled state of the block,
     * @param comment added from the user
     * @return read only object of class {@link TurnAction}.
     */
    public static <V> TurnAction<V> make(TurnDirection direction, MotionParam<V> param, boolean disabled, String comment) {
        return new TurnAction<V>(direction, param, disabled, comment);
    }

    /**
     * @return {@link TurnDirection} in which the robot will drive
     */
    public TurnDirection getDirection() {
        return this.direction;
    }

    /**
     * @return {@link MotionParam} for the motor (speed and distance in which the motors are set).
     */
    public MotionParam<V> getParam() {
        return this.param;
    }

    @Override
    public String toString() {
        return "TurnAction [direction=" + this.direction + ", param=" + this.param + "]";
    }

    @Override
    protected V accept(AstVisitor<V> visitor) {
        return visitor.visitTurnAction(this);
    }
}