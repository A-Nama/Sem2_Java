abstract class Employee {
    int ID;
    double salary;

    abstract void calculateSalary();
    abstract void displayinfo();
}

    class Manager extends Employee {
        public int MonthsWork;
        public String Name, Team;

        Manager(String Name, String Team, int ID, int MonthsWork) {
            this.Name = Name;
            this.Team = Team;
            this.ID = ID;
            this.MonthsWork = MonthsWork;
        }

        void calculateSalary() {
            this.salary = MonthsWork * 200.55;
        }

        @Override
        void displayinfo() {
            System.out.println("Manager");
            System.out.println("Name : " + Name);
            System.out.println("Team : " + Team);
            System.out.println("ID : " + ID);
            System.out.println("Salary : " + salary);
        }
    }

    class Programmer extends Employee {
        public int Projects;
        public String Name, Role;

        Programmer(String Name, String Role, int Projects, int ID) {
            this.Name = Name;
            this.Role = Role;
            this.Projects = Projects;
            this.ID = ID;
        }

        void calculateSalary() {
            this.salary = Projects * 555.55;
        }

        void displayinfo() {
            System.out.println("Programmer");
            System.out.println("Name : " + Name);
            System.out.println("Role : " + Role);
            System.out.println("ID : " + ID);
            System.out.println("Salary : " + salary);
        }
    }

public class MainEmp{
    public static void listEmployees(Employee[] emp){
        System.out.println("Employee Details :");
        System.out.println();
        for (Employee e : emp){
            e.displayinfo();
            System.out.println();
        }
    }

public static void main(String[] args) {
    Programmer p1 = new Programmer("Anjana", "Software Engineer", 20, 1006);
    p1.calculateSalary();

    System.out.println(" ");

    Manager m1 = new Manager("Majida", "HR", 1012, 5);
    m1.calculateSalary();
    
    Employee employee[]={p1,m1};
    MainEmp.listEmployees(employee);
}
}
