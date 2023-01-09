package bfpathing;

import java.io.*;
import java.util.*;

public class Generator {
    public static void main(String[]args) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader(
                System.getProperty("user.dir")+"/src/bfpathing/declarationsInput.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int senseRadius = Integer.parseInt(st.nextToken());
        int mapCount = Integer.parseInt(st.nextToken());

        //read in range parameters and construct map
        int senseRange = (int)Math.sqrt(senseRadius);
        int[][] arr = new int[2 * senseRange + 1][2 * senseRange +1];

        int[]x = new int[mapCount+1];
        int[]y = new int[mapCount+1];
        boolean[]ran = new boolean[mapCount+1];
        HashSet<Integer>[] connected = new HashSet[mapCount+1];
        ran[0] = true;

        for(int i =0; i <connected.length; i++){
            connected[i] = new HashSet<Integer>();
        }



        x[0] = senseRange;
        y[0] = senseRange;


        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(System.getProperty("user.dir")
                        +"/src/bfpathing/output_declarations.txt")));

        String dirInput = br.readLine();
        String distInput = br.readLine();
        String costInput = br.readLine();
        String mapLocInput = br.readLine();

        for(int i = 9; i<=mapCount; i++){
            pw.println(dirInput.replace("*", i+""));
        }

        for(int i = 1; i<=mapCount; i++){
            pw.println(distInput.replace("*", i+""));
        }

        for(int i = 9; i<=mapCount; i++){
            pw.println(costInput.replace("*", i+""));
        }

        for(int i = 1; i<=mapCount; i++){
            pw.println(mapLocInput.replace("*", i+""));
        }



        br = new BufferedReader(new FileReader(
                System.getProperty("user.dir")+"/src/bfpathing/directionInit.txt"));

        String line;
        while((line = br.readLine())!= null){
            pw.println(line);
        }

        pw.close();

        br = new BufferedReader(new FileReader(
                System.getProperty("user.dir")+"/src/bfpathing/distCostInit.txt"));


        pw = new PrintWriter(new BufferedWriter(
                new FileWriter(System.getProperty("user.dir")+"/src/bfpathing/output_distcost.txt")));

        String distInit = br.readLine();
        String costInit = br.readLine();

        for(int i = 1; i<=mapCount; i++){
            pw.println(distInit.replace("*", i+""));
        }

        for(int i = 9; i<=mapCount; i++){
            pw.println(costInit.replace("*", i+""));
        }
        pw.close();


        br = new BufferedReader(new FileReader(
                System.getProperty("user.dir")+"/src/bfpathing/mapLocationInit.txt"));

        pw = new PrintWriter(new BufferedWriter(
                new FileWriter(System.getProperty("user.dir")+"/src/bfpathing/output_maplocinit.txt")));

        String mapInit = br.readLine();
        int locCount = 0;

        x[0] = senseRange;
        y[0] = senseRange;
        arr[senseRange][senseRange]=0;
        while((line = br.readLine())!= null){
            st = new StringTokenizer(line);
            String dir = st.nextToken();
            int moves = Integer.parseInt(st.nextToken());
            int xOffset =0;
            int yOffset =0;
            String direction;

            if(dir.equals("N")){
                yOffset = -1;
                direction = "NORTH";
            } else if(dir.equals("NW")){
                xOffset = -1;
                yOffset = -1;
                direction = "NORTHWEST";
            } else if(dir.equals("W")){
                xOffset = -1;
                direction = "WEST";
            } else if(dir.equals("SW")){
                xOffset = -1;
                yOffset = 1;
                direction = "SOUTHWEST";
            } else if(dir.equals("S")){
                yOffset = 1;
                direction = "SOUTH";
            } else if(dir.equals("SE")){
                xOffset = 1;
                yOffset = 1;
                direction = "SOUTHEAST";
            }else if(dir.equals("E")){
                xOffset = 1;
                direction = "EAST";
            } else{
                xOffset = 1;
                yOffset = -1;
                direction = "NORTHEAST";
            }


            for(int i = 0; i< moves; i++){
                pw.println(mapInit.replace("*1",locCount+"" )
                        .replace("*0", (locCount+1)+"")
                        .replace("*d", direction));
                locCount ++;

                x[locCount] = x[locCount-1]+xOffset;
                y[locCount] = y[locCount-1]+yOffset;
                arr[y[locCount]][x[locCount]]=locCount;
            }
        }

        for(int i = 0; i< arr.length; i++){
            for(int j= 0; j< arr[0].length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

        pw.close();


        br = new BufferedReader(new FileReader(
                System.getProperty("user.dir")+"/src/bfpathing/perLocationInput.txt"));

        pw = new PrintWriter(new BufferedWriter(
                new FileWriter(System.getProperty("user.dir")+"/src/bfpathing/output_perlocation.txt")));

        String onMap = br.readLine();
        String distCenter = br.readLine() +"\n" + br.readLine()+"\n"+ br.readLine();

        String cost = br.readLine();
        String distNonCenter = br.readLine() +"\n" + br.readLine()+"\n"
                + br.readLine()+"\n"+ br.readLine();

        for(int i = 1; i<=8; i++ ){
            pw.println(onMap.replace("*0", i+""));
            pw.println(distCenter.replace("*0", i+""));
            pw.println("}");
            ran[i] = true;
        }




        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for(int n = 0; n < 2; n++){

                for(int i = start; i<=end; i++){
                    boolean newConnect = false;
                    String perLocation = "";
                    perLocation += onMap.replace("*0", i+"")+"\n";
                    if(!ran[i]){
                        perLocation += cost.replace("*0", i+"")+"\n";
                    }
                    for(int j = -1; j<=1; j++){
                        for(int k = -1; k<=1; k++){
                            if (y[i]+j< arr.length && y[i]+j >= 0 && x[i]+k< arr[0].length && x[i]+k >=0){
                                if(j!=0 || k !=0){
                                    int second = arr[y[i]+j][x[i]+k];
                                    if(second!=0 && ran[second] && !connected[i].contains(second)){
                                        int yDiff = Math.abs((y[i]-senseRange))-Math.abs((y[second]-senseRange));
                                        int xDiff = Math.abs((x[i]-senseRange))-Math.abs((x[second]-senseRange));


                                        if(xDiff > 0 || yDiff > 0 ){
                                            perLocation+= distNonCenter.replace("*0", i+"")
                                                    .replace("*1", second+"")+"\n";
                                            connected[i].add(second);
                                            newConnect = true;
                                        }
                                    }
                                }
                            }


                        }
                    }
                    ran[i] = true;

                    if(newConnect){
                        pw.println(perLocation);
                        pw.println("}");
                    }


                }
            }

        }

        pw.close();


        br = new BufferedReader(new FileReader(
                System.getProperty("user.dir")+"/src/bfpathing/switchInput.txt"));

        pw = new PrintWriter(new BufferedWriter(
                new FileWriter(System.getProperty("user.dir")+"/src/bfpathing/output_switch.txt")));


        pw.println(br.readLine());
        pw.println(br.readLine());

        String xSwitch = br.readLine()+"\n"+br.readLine();
        String ySwitch = br.readLine()+"\n"+br.readLine()+"\n"+br.readLine();

        for(int i = -senseRange; i <= senseRange; i++){
            pw.println(xSwitch.replace("*x", i+""));

            for(int j = senseRange; j >= -senseRange; j--){
                if(arr[senseRange+i][senseRange+j] != 0){
                    pw.println(ySwitch.replace("*y", (-j)+"")
                            .replace("*0", arr[senseRange+j][senseRange+i]+" "));
                    pw.println("}");
                }

            }
            pw.println("break;");
            pw.println("}");
        }
        pw.close();


        br = new BufferedReader(new FileReader(
                System.getProperty("user.dir")+"/src/bfpathing/targetCalcInput.txt"));

        pw = new PrintWriter(new BufferedWriter(
                new FileWriter(System.getProperty("user.dir")+"/src/bfpathing/output_targetcalc.txt")));

        pw.println(br.readLine());
        pw.println(br.readLine());
        pw.println(br.readLine());
        pw.println(br.readLine());

        String gainCalc = br.readLine() + "\n"+ br.readLine()
                + "\n"+ br.readLine()
                + "\n"+ br.readLine() + "\n"+ br.readLine();
        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()){
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for(int i = start; i<=end; i++){
                pw.println(gainCalc.replace("*0", i+""));
            }
        }

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());

            pw.println(gainCalc.replace("*0", n+""));
        }

        pw.println("return ans;");
        pw.close();




    }
}
