import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static List<String> fetchItemsToDisplay(int sortParameter, int sortOrder, 
        int itemsPerPage, int pageNumber, int numOfItems, HashMap<String, int[]> dataArray) {

        // WRITE YOUR CODE HERE
        List<String> items = new ArrayList(dataArray.keySet()); // create a list of item names
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String item1, String item2) {
                int res;
                if (sortParameter == 0) { // compare item name alphabetical
                    res = item1.compareTo(item2);
                } else {
                    // compare by relevance or price. sortParamter - 1 because subtracting the item name spot
                    res = dataArray.get(item1)[sortParameter - 1] - dataArray.get(item2)[sortParameter - 1];
                }
                return res * (sortOrder == 0 ? 1 : -1); // if reverse order, then * -1
            }
        };
        
        items.sort(comparator);
        int startIndex = itemsPerPage * pageNumber;
        return items.subList(startIndex, Math.min(startIndex + itemsPerPage, items.size()));
    }

    // 测试
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int sortParameter = Integer.parseInt(scanner.nextLine());
        int sortOrder = Integer.parseInt(scanner.nextLine());
        int itemsPerPage = Integer.parseInt(scanner.nextLine());
        int pageNumber = Integer.parseInt(scanner.nextLine());
        int numOfItems = Integer.parseInt(scanner.nextLine());
        String [][] dataArray = new String[numOfItems][3];
        HashMap<String, int[]> mapOfItems = new HashMap<>();
        for (int i=0; i < numOfItems; i++) {
            String [] line = scanner.nextLine().split(" ");
            for (int j=0; j<3; j++) {
              int[] intPair = {Integer.parseInt(line[1]),Integer.parseInt(line[2])};
              mapOfItems.put(line[0], intPair);
            }
        }
        System.out.println(fetchItemsToDisplay(sortParameter, sortOrder, itemsPerPage, pageNumber, numOfItems,  mapOfItems).stream().collect(Collectors.joining(" ")));
    }
}