package problems.triangle;

public class Judge {

    //x,y,z分别为三角形的三条边长，函数返回字串
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
            return "长度超出范围";
        }
    }

    //判断输入的三条边长是否合法
    public boolean isLegalLength(int x,int y,int z){
        return x > 0 && y > 0 && z > 0 && x < 201 && y < 201 && z < 201;
    }

    //判断输入三条边长是否能构成三角形
    public boolean isTriangle(int x,int y,int z){
        return x + y > z && x + z > y && y + z > x;
    }

    //判断构成的三角形是否至少有两条边相等
    public boolean isEquilateral(int x,int y,int z){
        return x == y && x == z;
    }

    //判断三角形的三条边是否相等
    public boolean isIsosceles(int x,int y,int z){
        return x == y || x == z || y == z;
    }

}
