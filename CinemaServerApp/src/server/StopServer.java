/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Monika
 */
public class StopServer extends Thread{
     boolean end = false;
    StartServer ss;

    public StopServer(StartServer ss)
    {
        this.ss = ss;
    }
    
    @Override
    public void run()
    {
        while(!end)
        {
            if(ss.isInterrupted())
            {               
                    ss.stoppedServer();
                    end = true;  
            }
        }
    }   
}
