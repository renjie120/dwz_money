package dwz.present.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import dwz.framework.constants.Constants;
import dwz.present.BaseAction;

public class ValidationCodeAction extends BaseAction {
	// Verify Image Dimensions
	private int	width			= 76;
	private int	height			= 29;

	// Verify Code Numbers
	private int	codeCount		= 4;

	private int	x				= 15;

	Color		bgColor			= new Color(254, 233, 192);

	char[]		codeSequence	= { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9'			};

	public ValidationCodeAction() {
		super();
		this.width = appConfig.getInt("validation-code.width");
		this.height = appConfig.getInt("validation-code.height");
		this.codeCount = appConfig.getInt("validation-code.code-count");
	}

	public String generate() {
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		Random random = new Random();

		// Set the color to fill with
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		g.setColor(bgColor);
		g.drawRect(0, 0, width - 1, height - 1);

		// Generate dash lines randomly
		g.setColor(bgColor);
		for (int i = 0; i < 150; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// Save randomCode
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		// Generate randomCode
		for (int i = 0; i < codeCount; i++) {

			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);

			red = random.nextInt(155);
			green = random.nextInt(155);
			blue = random.nextInt(155);

			g.setColor(new Color(red, green, blue));
			Font font = new Font("Arial", Font.BOLD, (int) (14 + 10 * Math
					.random()));
			g.setFont(font);
			g.drawString(strRand, i * x + 6, height - 4);
			randomCode.append(strRand);
		}

		HttpSession session = request.getSession();
		session.setAttribute(Constants.VALIDATION_CODE, randomCode.toString());

		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				sos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
