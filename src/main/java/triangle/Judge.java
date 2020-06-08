package triangle;

/**
 * @author PC
 */
public class Judge {
    /**
     *
     * @param x 三角形边长之一
     * @param y 三角形边长之一
     * @param z 三角形边长之一
     * @return 是否能构成三角形或三角形数量
     */
    public String judge(int x, int y, int z){
        if(isLegalLength(x, y, z)){
            if(isTriangle(x, y, z)){
                if(isEquilateral(x, y, z)){
                    return "等边三角形";
                }
                else if(isIsosceles(x, y, z)){
                    return "等腰三角形";
                }
                else {
                    return "普通三角形";
                }
            }
            else{
                return "不构成三角形";
            }
        }
        else {
            return "含有非正数长度";
        }
    }

    /**
     *
     * @param x 三角形边长之一
     * @param y 三角形边长之一
     * @param z 三角形边长之一
     * @return 三边长是否均大于0 是：true 否：false
     */
    public boolean isLegalLength(int x,int y,int z){
        return x > 0 && y > 0 && z > 0;
    }

    /**
     *
     * @param x 三角形边长之一
     * @param y 三角形边长之一
     * @param z 三角形边长之一
     * @return 是否能构成三角形 能：true 不能：false
     */
    public boolean isTriangle(int x,int y,int z){
        return x + y > z && x + z > y && y + z > x;
    }

    /**
     *
     * @param x 三角形边长之一
     * @param y 三角形边长之一
     * @param z 三角形边长之一
     * @return 是否为等腰三角形 是：true 否：false
     */
    public boolean isEquilateral(int x,int y,int z){
        return x == y && x == z;
    }

    /**
     *
     * @param x 三角形边长之一
     * @param y 三角形边长之一
     * @param z 三角形边长之一
     * @return 是否为等边三角形 是：true 否：false
     */
    public boolean isIsosceles(int x,int y,int z){
        return x == y || x == z || y == z;
    }
}
