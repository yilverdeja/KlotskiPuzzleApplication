package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Model;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.MovePieceController;
import controller.ResetPuzzleController;
import controller.SelectPieceController;

import java.awt.Component;

public class KlotskiApplication extends JFrame {

	private JPanel contentPane;
	Model model;
	JLabel numberOfMoves;
	PuzzleView puzzleView;
	JButton btnResetPuzzle;
	JButton btnUp, btnDown, btnLeft, btnRight;
	
	public JLabel getMoveCounter() {
		return numberOfMoves;
	}
	
	public PuzzleView getPuzzleView() {
		return puzzleView;
	}
	
	public void setNumberOfMoves(int numMoves) {
		numberOfMoves.setText(String.valueOf(numMoves));
	}

	/**
	 * Create the frame.
	 */
	public KlotskiApplication(Model model) {
		setResizable(false);
		this.model = model;
		setTitle("Klotski Puzzle");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, model.PUZZLE_WIDTH*2, model.PUZZLE_HEIGHT*2);
		setFocusable(true);
		requestFocusInWindow();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMoves = new JLabel("Moves:");
		lblMoves.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblMoves.setBounds(180, 100, 92, 26);
		contentPane.add(lblMoves);
		
		numberOfMoves = new JLabel("0");
		numberOfMoves.setFont(new Font("Tahoma", Font.PLAIN, 26));
		numberOfMoves.setHorizontalAlignment(SwingConstants.TRAILING);
		numberOfMoves.setBounds(200, 100, 92, 26);
		contentPane.add(numberOfMoves);
		
		puzzleView = new PuzzleView(model);
		puzzleView.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new SelectPieceController(e.getPoint(), model, puzzleView).selectPiece();
			}
		});
		
		puzzleView.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		puzzleView.setBounds(model.PUZZLE_WIDTH/2, model.PUZZLE_HEIGHT/2, model.PUZZLE_WIDTH, model.PUZZLE_HEIGHT);
		contentPane.add(puzzleView);
		
		btnResetPuzzle = new JButton("Reset Puzzle");
		btnResetPuzzle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetPuzzleController().resetPuzzle(model, KlotskiApplication.this);
			}
		});
		btnResetPuzzle.setBounds(160, 475, 170, 35);
		contentPane.add(btnResetPuzzle);
		
		btnUp = new JButton("^");
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, KlotskiApplication.this, KeyEvent.VK_UP).tryMove();
			}
		});
		btnUp.setBounds(395, 250, 45, 25);
		contentPane.add(btnUp);
		
		btnDown = new JButton("v");
		btnDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, KlotskiApplication.this, KeyEvent.VK_DOWN).tryMove();
			}
		});
		btnDown.setBounds(395, 330, 45, 25);
		contentPane.add(btnDown);
		
		btnLeft = new JButton("<");
		btnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, KlotskiApplication.this, KeyEvent.VK_LEFT).tryMove();
			}
		});
		btnLeft.setBounds(370, 290, 45, 25);
		contentPane.add(btnLeft);
		
		btnRight = new JButton(">");
		btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, KlotskiApplication.this, KeyEvent.VK_RIGHT).tryMove();
			}
		});
		btnRight.setBounds(420, 290, 45, 25);
		contentPane.add(btnRight);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, lblMoves, numberOfMoves, puzzleView, btnResetPuzzle, btnUp, btnDown, btnLeft, btnRight}));
	}
}
