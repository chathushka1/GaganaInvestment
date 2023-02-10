package lk.ijse.microfinance.model;

public class DebtorModel {
   /* public static boolean register(DebtorDTO debtor) throws SQLException, ClassNotFoundException {
*//*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
*//*
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("INSERT INTO Debtor VALUES(?,?,?,?,?,?,?)");
//        pstm.setString(1,debtor.getdID());
//        pstm.setString(2,debtor.getName());
//        pstm.setString(3,debtor.getAddress());
//        pstm.setString(4,debtor.getNic());
//        pstm.setDouble(5,debtor.getAmountDeu());
//        pstm.setString(6,debtor.getTelephone());
//        pstm.setString(7,debtor.getEmployeeId());
//
//        return pstm.executeUpdate()>0 ;

        String sql = "INSERT INTO Debtor VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,debtor.getdID(),debtor.getName(),debtor.getAddress(),debtor.getNic(),debtor.getAmountDeu(),debtor.getTelephone(),debtor.getEmployeeId());
    }*/
}
