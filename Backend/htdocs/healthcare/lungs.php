
<div id="overlay" class="lungs">
    <div id="content">
        <button onclick="off()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback">
            <p id="sign">LUNGS SPECIALIST</p>
            <hr>
            <?php
            $lungs="select * from doc_login where d_specialization='lungs'";
            $exe_lungs=$con->query($lungs);
            $lungs_special=mysqli_fetch_array($exe_lungs);
            $lungs_doc_id=$lungs_special['d_id'];
            $lungs_doc_name=$lungs_special['d_name'];
            $lungs_doc_experience=$lungs_special['d_experience'];
            $lungs_doc_age=$lungs_special['age'];
            $lungs_doc_number=$lungs_special['d_mobile'];
            $lungs_doc_gender=$lungs_special['gender'];
            $lungs_doc_blood=$lungs_special['bloodgroup'];
            $lungs_doc_address=$lungs_special['address'];
            ?>
            <img id="side" src="images/cardiologist.jpg">
            <table>
                <tr>
                    <td>ID</td>
                    <td><?php echo $lungs_doc_id;?></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><?php echo $lungs_doc_name;?></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><?php echo $lungs_doc_experience;?></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td><?php echo $lungs_doc_age;?></td>
                </tr>
                <tr>
                    <td>Mobile Number</td>
                    <td><?php echo $lungs_doc_number;?></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><?php echo $lungs_doc_gender;?></td>
                </tr>
                <tr>
                    <td>Blood Group</td>
                    <td><?php echo $lungs_doc_blood;?></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><?php echo $lungs_doc_address;?></td>
                </tr>
            </table>
            <h6></h6>
        </div>
    </div>
</div>
<script>

    function lungs() {
        document.getElementsByClassName("lungs").style.display = "block";
    }

    function off() {
        document.getElementsByClassName("lungs").style.display = "none";
    }
</script>