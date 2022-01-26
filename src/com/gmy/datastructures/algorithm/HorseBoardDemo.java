package com.gmy.datastructures.algorithm;


import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author guomaoyang
 * @Date 2021/1/14
 */
public class HorseBoardDemo {
    public static void main(String[] args) {
        int row = 8;
        int line = 8;

        Point currPoint = new Point(2,2);
        Board board = new Board(row, line);
        long l = System.currentTimeMillis();
        board.step(currPoint,new AtomicInteger(0));
        System.out.println("耗时：" + (System.currentTimeMillis()-l)+"ms");

        board.show();

    }
}
class Board extends Timer {
    int row;// 行
    int line;// 列
    int[][] board;// 棋盘

    public Board(int row,int line){
        this.row = row;
        this.line = line;
        this.board = new int[row][line];
    }

    public void show(){
        for (int i = 0; i < row; i++) {
            System.out.print(Arrays.toString(board[i]));
            System.out.println();
        }
    }

    public void step(Point currPoint, AtomicInteger step){
        step.incrementAndGet();
        board[currPoint.x][currPoint.y] = step.get();
        if(step.get() >= row * line){
            return ;
        }
        // 获取下一步
        List<Point> nextStepList = getSortStepList(currPoint);
        if(nextStepList.size() == 0 && step.get() < row*line){// 没有走完并且无路可走
            step.decrementAndGet();
            board[currPoint.x][currPoint.y] = 0;
            return ;
        }
        for (Point point : nextStepList) {
            step(point, step);
            if(step.get() >= row * line){
                return ;
            }
        }
        if(step.get() < row*line){
            step.decrementAndGet();
            board[currPoint.x][currPoint.y] = 0;
            return ;
        }
    }
    public List<Point> getSortStepList(Point currPoint){
        List<Point> nextStepList = getNextStepList(currPoint);
        if(nextStepList.size() > 0){
            Collections.sort(nextStepList,(a,b)->{
                List<Point> aList = getNextStepList(a);
                List<Point> bList = getNextStepList(b);
                if(aList.size() < bList.size()){
                    return -1;
                }else if(aList.size() > bList.size()){
                    return 1;
                }
                return 0;
            });
        }
        return nextStepList;
    }
    public List<Point> getNextStepList(Point currPoint){
        List<Point> points = new ArrayList<>();
        if(currPoint.x + 2 < row && currPoint.y -1 >= 0){
            Point point = new Point(currPoint.x + 2, currPoint.y - 1);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }

        if(currPoint.x + 1 < row && currPoint.y -2 >= 0){
            Point point = new Point(currPoint.x + 1, currPoint.y - 2);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }

        if(currPoint.x + 2 < row && currPoint.y +1 < line){
            Point point = new Point(currPoint.x + 2, currPoint.y +1);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }

        if(currPoint.x + 1 < row && currPoint.y +2 < line){
            Point point = new Point(currPoint.x + 1, currPoint.y +2);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }

        if(currPoint.x -2 >= 0 && currPoint.y -1 >= 0){
            Point point = new Point(currPoint.x -2, currPoint.y -1);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }

        if(currPoint.x -1 >= 0 && currPoint.y -2 >= 0){
            Point point = new Point(currPoint.x -1, currPoint.y -2);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }

        if(currPoint.x -2 >= 0 && currPoint.y +1 < line){
            Point point = new Point(currPoint.x -2, currPoint.y +1);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }
        if(currPoint.x -1 >= 0 && currPoint.y +2 < line){
            Point point = new Point(currPoint.x -1, currPoint.y +2);
            if(board[point.x][point.y] == 0){// 并且还没有走过这一步
                points.add(point);
            }
        }

        return points;
    }
}