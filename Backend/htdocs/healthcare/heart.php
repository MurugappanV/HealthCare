
<div id="overlay">
    <div id="content">
        <button onclick="off()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback">
            <p id="sign">HEART SPECIALIST</p>
            <hr>
            <?php
              $heart="select * from doc_login where d_specialization='heart'";
                $exe_heart=$con->query($heart);
                $heart_special=mysqli_fetch_array($exe_heart);
                $heart_doc_id=$heart_special['d_id'];
                $heart_doc_name=$heart_special['d_name'];
                $heart_doc_experience=$heart_special['d_experience'];
                $heart_doc_age=$heart_special['age'];
                $heart_doc_number=$heart_special['d_mobile'];
                $heart_doc_gender=$heart_special['gender'];
                $heart_doc_blood=$heart_special['bloodgroup'];
                $heart_doc_address=$heart_special['address'];
            ?>
            <img id="side" src="images/cardiologist.jpg">
            <table>
                <tr>
                    <td>ID</td>
                    <td><?php echo $heart_doc_id;?></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><?php echo $heart_doc_name;?></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><?php echo $heart_doc_experience; ?></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td><?php echo $heart_doc_age;?></td>
                </tr>
                <tr>
                    <td>Mobile Number</td>
                    <td><?php echo $heart_doc_number;?></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><?php echo $heart_doc_gender;?></td>
                </tr>
                <tr>
                    <td>Blood Group</td>
                    <td><?php echo $heart_doc_blood;?></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><?php echo $heart_doc_address;?></td>
                </tr>
            </table>
            <h6></h6>
        </div>
    </div>
</div>
<script>

    function heart() {
        document.getElementById("overlay").style.display = "block";
    }

    function off() {
        document.getElementById("overlay").style.display = "none";
    }
</script>