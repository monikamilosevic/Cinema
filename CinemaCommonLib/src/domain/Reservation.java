/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Monika
 */
public class Reservation implements GeneralEntity{
    
    private int id;
    private double price;
    private double discount;
    private double discountPrice;
    private int tickets;
    private Projection projection;
    private Member member;

    public Reservation() {
    }

    public Reservation(int id, double price, double discount, double discountPrice, int tickets, Projection projection, Member member) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.discountPrice = discountPrice;
        this.tickets = tickets;
        this.projection = projection;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    

    @Override
    public String getTableName() {
        return " reservation "; 
    
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws Exception {
        List<GeneralEntity> list = new ArrayList<>();
        while (rs.next()) {            
            list.add(new Reservation(rs.getInt("r.reservationID"), rs.getDouble("r.price"), rs.getInt("r.discount"),
                    rs.getDouble("r.discountPrice"), rs.getInt("r.tickets"), 
                    new Projection(rs.getInt("p.projectionID"), new Date(rs.getTimestamp("p.date").getTime()), 
                    rs.getDouble("p.price"), rs.getInt("p.score"), rs.getInt("p.freeSeats"), 
                            new Movie(rs.getInt("movie.movieID"), rs.getString("movie.title"), rs.getString("movie.genre")), null), 
                            new Member(rs.getInt("m.memberID"), rs.getString("m.firstname"), rs.getString("m.lastname"), rs.getString("m.email"), rs.getInt("m.totalScore"))));
        }        
        return list;
    }

    @Override
    public String getColumnName() {
        return " price, discount, discountPrice, tickets, projectionID, memberID ";
    }

    @Override
    public String getValueForInsert() {
        return " "+price+", "+discount+", "+discountPrice+", "+tickets+", "+projection.getId()+", "+member.getId()+" ";
    }

    @Override
    public String getValueForUpdate() {
        return " price = "+price+", discount = "+discount+", tickets = "+tickets+", projectionID ="+
                projection.getId() +", memberID = "+member.getId()+" ";
    }

    @Override
    public String getWhere() {
        return " reservationID = "+id;
    }

    @Override
    public String getColumnForSelection() {
        return " r.*, p.*, m.*, movie.* ";
    }

    @Override
    public String getAlias() {
        return " r ";
    }

    @Override
    public String getJoin() {
        return " JOIN member m on r.memberID = m.memberID JOIN projection p on r.projectionID = p.projectionID "
                + "JOIN movie movie on p.movieID = movie.movieID";
    }

    @Override
    public String getWhereForSelect() {
        if(projection.getMovie().getTitle()!=null && member.getFirstname() != null && member.getLastname()!=null ){
            String s = " where movie.title LIKE '%"+projection.getMovie().getTitle()+"%' "
                    + "OR m.firstname LIKE '%"+member.getFirstname()+"%' OR m.lastname LIKE '%"+member.getLastname()+"%' ";
            if(member.getId() != -1)
                s+= "OR m.memberID ="+member.getId()+" ";
            return s;
        }
        return "";
    }

    @Override
    public String getGroup() {
        return " ";
    }

    @Override
    public String getMaxPrimary() {
        return "";
    }
    
}
