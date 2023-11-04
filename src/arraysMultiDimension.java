public class arraysMultiDimension {
    
    public static void main(String[] args) {

        //2d array = array of rays (Has a number of rows and colums the data are stored in


        String [][] cars = {

            {"Camaro", "Mustang", "Corvette"},
            {"Mustang", "Labmdo", "Tesla"},
            {"Ferrari", "Lambo", "Maserati"}
        };

        for (int i=0; i < cars.length; i++) {

            System.out.println();
            for(int j = 0; j < cars.length; j++) {

                System.out.println(cars[i][j]+ " ");
            }

            
        }

        //another way to populate a specific postition in an array. 

        String [][] cars2 = new String[3][3];

        cars2[0][0] = "Camaro";
        cars2[0][1] = "Tesla";
        cars2[0][2] = "Silverado";
        cars2[1][0] = "COrvette";
        cars2[1][1] = "Maserati";
        cars2[1][2] = "Bugati";
        cars2[2][0] = "F-150";
        cars2[2][1] = "Porsche";
        cars2[2][2] = "Audi";

        for (int k=0; k < cars2.length; k++) {

            System.out.println();
            for(int l = 0; l < cars2[k].length; l++) {

                System.out.print(cars2[k][l]+ " ");
            }

            
        }
        
    }
}
