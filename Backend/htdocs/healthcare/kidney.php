
<div id="overlay" class="kidney">
    <div id="content">
        <button onclick="off()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback">
            <p id="sign">KIDNEY SPECIALIST</p>
            <hr>
            <?php
            $kidney="select * from doc_login where d_specialization='kidney'";
            $exe_kidney=$con->query($kidney);
            $kidney_special=mysqli_fetch_array($exe_kidney);
            $kidney_doc_id=$kidney_special['d_id'];
            $kidney_doc_name=$kidney_special['d_name'];
            $kidney_doc_experience=$kidney_special['d_experience'];
            $kidney_doc_age=$kidney_special['age'];
            $kidney_doc_number=$kidney_special['d_mobile'];
            $kidney_doc_gender=$kidney_special['gender'];
            $kidney_doc_blood=$kidney_special['bloodgroup'];
            $kidney_doc_address=$kidney_special['address'];
            ?>
            <img id="side" src="images/cardiologist.jpg">
            <table>
                <tr>
                    <td>ID</td>
                    <td><?php echo $kidney_doc_id;?></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><?php echo $kidney_doc_name;?></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><?php echo $kidney_doc_experience;?></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td><?php echo $kidney_doc_age;?></td>
                </tr>
                <tr>
                    <td>Mobile Number</td>
                    <td><?php echo $kidney_doc_number;?></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><?php echo $kidney_doc_gender;?></td>
                </tr>
                <tr>
                    <td>Blood Group</td>
                    <td><?php echo $kidney_doc_blood;?></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><?php echo $kidney_doc_address;?></td>
                </tr>
            </table>
            <h6></h6>
        </div>
    </div>
</div>
<script>

    function kidney() {
        document.getElementsByClassName("kidney").style.display = "block";
    }

    function off() {
        document.getElementsByClassName("kidney").style.display = "none";
    }
</script>