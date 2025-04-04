import java.util.*;
class Process {
    int pid;
    int burstTime;
    int priority;
    int remainingTime;
    int waitingTime;
    int turnAroundTime;
    Process next;
    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}
class RoundRobinScheduler {
    Process head = null;
    Process tail = null;
    int timeQuantum;
    RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }
    void removeProcess(Process toRemove) {
        if (head == null || toRemove == null) return;
        if (head == toRemove && head == tail) {
            head = tail = null;
            return;
        }
        Process current = head, prev = tail;
        do {
            if (current == toRemove) {
                prev.next = current.next;
                if (current == head) head = current.next;
                if (current == tail) tail = prev;
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }
    void simulate() {
        if (head == null) return;
        int currentTime = 0;
        Process current = head;
        List<Process> completedProcesses = new ArrayList<>();
        while (head != null) {
            if (current.remainingTime > 0) {
                int timeSpent = Math.min(timeQuantum, current.remainingTime);
                current.remainingTime -= timeSpent;
                currentTime += timeSpent;
                if (current.remainingTime == 0) {
                    current.turnAroundTime = currentTime;
                    current.waitingTime = current.turnAroundTime - current.burstTime;
                    completedProcesses.add(current);
                    Process toRemove = current;
                    current = current.next;
                    removeProcess(toRemove);
                } else {
                    current = current.next;
                }
            } else {
                current = current.next;
            }
            displayProcesses();
        }
        double totalWaitingTime = 0;
        double totalTurnAroundTime = 0;
        for (Process p : completedProcesses) {
            totalWaitingTime += p.waitingTime;
            totalTurnAroundTime += p.turnAroundTime;
        }
        System.out.printf("Average Waiting Time: %.2f\n", totalWaitingTime / completedProcesses.size());
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnAroundTime / completedProcesses.size());
    }
    void displayProcesses() {
        if (head == null) {
            System.out.println("No processes remaining.");
            return;
        }
        Process temp = head;
        System.out.println("Current Queue:");
        do {
            System.out.println("PID: " + temp.pid + " | Burst: " + temp.burstTime + " | Remaining: " + temp.remainingTime + " | Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }
}
public class RoundRobinCPU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();
        RoundRobinScheduler scheduler = new RoundRobinScheduler(tq);
        while (true) {
            System.out.println("1. Add Process\n2. Simulate Round-Robin\n3. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter PID, Burst Time, Priority: ");
                    scheduler.addProcess(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    break;
                case 2:
                    scheduler.simulate();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}