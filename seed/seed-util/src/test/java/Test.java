import com.swf.seed.util.PropUtil;


public class Test {
	public static void main(String[] args) {
		PropUtil.changePropFile("d:/a");
		System.out.println(PropUtil.getPropValue("a"));
	}
}
