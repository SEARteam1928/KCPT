import java.io.File;

public class Main {
    public static void main(String[] args) {

        try{
            String id = "1BV4L84LI4rb7Pq5xxA3-RGqyaDuNYCaf";
            String deleteDirectory = "rm /home/student/exelFiles/timetable.xls";
            ProcessBuilder pbDelete = new ProcessBuilder("/bin/bash","-c", deleteDirectory);
            pbDelete.start();
            Thread.sleep(2000);
            System.out.println("Deleted!");


            String downloadExel =
                    "wget --content-disposition " +
                            "'https://drive.google.com/uc?export=download&id=" +
                            "1Zgti_EG2MZtoMYywejl4yqAfzwDxQM-q' " +
                            "-O /home/student/exelFiles/timetable.xls " +
                            "-P /home/student/exelFiles";
            ProcessBuilder pb = new ProcessBuilder("/bin/bash","-c", downloadExel);
            pb.start();
            Thread.sleep(5000);
            System.out.println("Downloaded!");


            String parsingExel =
                    "cd /home/student/exelFiles\npython3 /home/student/exelFiles/updatedb.py";
            ProcessBuilder pbparse = new ProcessBuilder("/bin/bash","-c", parsingExel);
            pbparse.start();
            Thread.sleep(6000);
            System.out.println("Parsed!");

            String sendInFirebase =
                    "cd /home/student/exelFiles/groups\nnodejs /home/student/exelFiles/groups/readAsFile.js";
            ProcessBuilder pbsend = new ProcessBuilder("/bin/bash","-c", sendInFirebase);
            pbsend.start();
            Thread.sleep(6000);
            System.out.println("Sended!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
