package files;

public class resources
{

    public static String placePostData()
    {
        String res="/maps/api/place/add/json";
        return res;
    }

    public static String placeGetData()
    {
        String res="maps/api/place/nearbysearch/json";
        return res;
    }

    public static String placeDeleteData()
    {
        String res="/maps/api/place/delete/json";
        return res;
    }




}
