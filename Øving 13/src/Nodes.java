public class Nodes{
    double[][] nodes; //To keep track over latitude and longitude

    public Nodes(int numberOfNodes) {
        nodes = new double[numberOfNodes][];
    }

    void addPlace(int nodeNumber, double longitude, double latitude) {
        nodes[nodeNumber] = new double[2];
        nodes[nodeNumber][0] = longitude;
        nodes[nodeNumber][1] = latitude;
    }
    //The HaversineDistance
    int calcDistance(int node1, int node2) {
        double r = 6371000;
        double longitude1 = nodes[node1][0] * (Math.PI/180);
        double longitude2 = nodes[node2][0] * (Math.PI/180);
        double latitude1 = nodes[node1][1] * (Math.PI/180);
        double latitude2 = nodes[node2][1] * (Math.PI/180);
        int res = (int) Math.floor((2 * r) * Math.asin(Math.sqrt(Math.pow(Math.sin(((longitude1 - longitude2) / 2)), 2) + Math.cos(longitude1)* Math.cos(longitude2) * Math.pow(Math.sin(((latitude1 - latitude2) / 2)), 2))));
        return res;
    }

}
