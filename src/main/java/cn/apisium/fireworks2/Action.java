package cn.apisium.fireworks2;

public final class Action {
    public final ActionType type;
    public final double[] args;
    public final boolean isRelative;
    public Action(final ActionType type, final double[] args, final boolean isRelative) {
        this.type = type;
        this.args = args;
        this.isRelative = isRelative;
    }
    public enum ActionType {
        MoveTo, // M = moveTo(M X,Y) ：将画笔移动到指定的坐标位置
        LineTo, // L = lineTo(L X,Y) ：画直线到指定的坐标位置
        HorizontalLineTo, // H = horizontal lineTo(H X)：画水平线到指定的X坐标位置
        VerticalLineTo, // V = (V Y)：画垂直线到指定的Y坐标位置
        CurveTo, // C = curveTo(C X1,Y1,X2,Y2,END_X,END_Y)：三次贝赛曲线
        SmoothCurveTo, // S = smooth curveTo(S X2,Y2,END_X,END_Y)：平滑曲率
        QuadraticBelzierCurve, // Q = quadratic Belzier curve(Q X,Y,ENDX,ENDY)：二次贝赛曲线
        SmoothQuadraticBelzierCurveTo, // T = smooth quadratic Belzier curveTo(T ENDX,ENDY)：映射
        EllipticalArc, // A = elliptical Arc(A RX,RY,X_ROTATION,FLAG1,FLAG2,X,Y)：弧线
        ClosePath // Z = closePath()：关闭路径,
    }
}
