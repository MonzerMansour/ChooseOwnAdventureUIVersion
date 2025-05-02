package Classes;

import Classes.Rooms.Room;
import Classes.Rooms.Rooms;
import Enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MapPanel extends javax.swing.JPanel {
    private Room currentRoom;

    public MapPanel(Room currentRoom) {
        this.currentRoom = currentRoom;
        this.add(new JLabel("Map"));
        this.currentRoom.setVisited();
        setRoomCords();
    }

    private void setRoomCords() {
        Rooms.allRooms.forEach(room -> {
            room.setLocation(0,0);
        });
        Rooms.CITY_CENTER.setLocation(775,125);
        Rooms.SAFEHOUSE.setLocation(775,50);
        Rooms.BUNKER.setLocation(575,50);
        Rooms.POLICE_STATION.setLocation(975,50);
        Rooms.MARKET.setLocation(975,125);
        Rooms.CITY_GATE.setLocation(575,125);
        Rooms.ZOMBIE_ALLEY.setLocation(575, 200);
        Rooms.HOSPITAL.setLocation(775, 200);
        Rooms.MILITARY_BASE.setLocation(975, 200);
        Rooms.MORGUE.setLocation(775, 275);
        Rooms.ZOMBIE_CASTLE_GATES.setLocation(575, 275);
        Rooms.ZOMBIE_CASTLE_MAIN_HALL.setLocation(575, 350);
        Rooms.ZOMBIE_CASTLE_KITCHEN.setLocation(375, 350);
        Rooms.ZOMBIE_CASTLE_PRISON.setLocation(175, 350);
        Rooms.ZOMBIE_CASTLE_SNACK_ROOM.setLocation(775, 350);
        Rooms.ZOMBIE_CASTLE_WEAPON_ROOM.setLocation(975, 350);
        Rooms.BEDROOM.setLocation(775, 425);
        Rooms.ZOMBIE_CASTLE_GRAND_HALL.setLocation(575, 425);
        Rooms.ZOMBIE_CASTLE_THRONE_ROOM.setLocation(575, 500);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int roomSize = 40;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawRoomConnections(g2, roomSize);
        drawRooms(g2, roomSize);
    }

    private void drawRooms(Graphics2D g2, int roomSize) {
        for (Room room: Rooms.allRooms) {
            if (!room.isVisited()) continue;
            drawRoom(g2, room,roomSize);
        }
    }

    private void drawRoom(Graphics2D g2, Room room, int roomSize) {
        int x = room.getX();
        int y = room.getY();

        g2.setColor(room==currentRoom?Color.GREEN:Color.BLACK);
        g2.fillRect(x, y, roomSize, roomSize);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(x, y, roomSize, roomSize);

        g2.drawString(room.getName(), x + 3, y + roomSize + 12);
    }

    private void drawRoomConnections(Graphics2D g2, int roomSize){
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(1));
        for (Room room: Rooms.allRooms) {
            if (!room.isVisited()) continue;
            for (Map.Entry<Direction, Room> entry : room.getAdjecent().entrySet()) {
                int x1 = room.getX() + roomSize / 2;
                int y1 = room.getY() + roomSize / 2;
                int x2 = entry.getValue().getX() + roomSize / 2;
                int y2 = entry.getValue().getY() + roomSize / 2;
                g2.drawLine(x1, y1, x2, y2);
            }
        }

    }

    public void moveIntoRoom(Room room){
        room.setVisited();
        this.currentRoom = room;
        repaint();

    }

}
